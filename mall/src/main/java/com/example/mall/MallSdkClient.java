package com.example.mall;

import com.xfw.mall.sdk.DefaultXfwForweMallClient;
import com.xfw.mall.sdk.XfwForweMallClient;
import com.xfw.mall.sdk.exceptions.ClientException;
import com.xfw.mall.sdk.model.req.area.QueryAddressListRequest;
import com.xfw.mall.sdk.model.rsp.area.Address;
import com.xfw.mall.sdk.model.rsp.area.QueryAddressListResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Component
public class MallSdkClient {

	private static final String APP_KEY = "AwzEgw0r";
	private static final String APP_SECRET = "3f60a6a0316f255794caf63c4ab44c84d4d04e8a";
	private static final String URL = "http://forwemall.forwe.store:3001/forwemall/api/v1";
	private static final String LOCAL_URL = "http://localhost:8082/api/v1";
	private static XfwForweMallClient client;

	public static XfwForweMallClient acsClient() {
		if (null == client) {
			client = new DefaultXfwForweMallClient(APP_KEY, APP_SECRET, URL);
		}
		return client;
	}

	public static void main(String[] args) throws ClientException {
		iterateAddress(1L);
	}

	/**
	 * //根据不同的parentId返回地址信息
	 */
	public static List<Address> getList(Long parentId) throws ClientException {
		QueryAddressListRequest request = new QueryAddressListRequest();
		request.setParentId(String.valueOf(parentId));
		QueryAddressListResponse response = MallSdkClient.acsClient().getXfwForweMallResponse(request);
		if (response.isSuccess()) {
			return response.getAddresses();
		}
		return null;
	}

	public static void iterateAddress(Long parentId) throws ClientException {
		List<Address> addressList = getList(parentId);
		//递归退出条件
		if (addressList == null) {
			return;
		}
		for (Address address : addressList) {
			System.out.println(address.getName() + " " + address.getCode());
			iterateAddress(address.getCode());
		}
	}
}



