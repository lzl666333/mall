//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.xfw.mall.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfw.mall.sdk.http.HttpClientUtils;
import com.xfw.mall.sdk.util.MapUtil;
import com.xfw.mall.sdk.util.SignUtil;
import com.xfw.mall.sdk.util.StringUtil;

import java.util.Map;
import java.util.TreeMap;

public class DefaultXfwForweMallClient implements XfwForweMallClient {
	private String appKey;
	private String appSecret;
	private String appUrl;

	public DefaultXfwForweMallClient(String appKey, String appSecret, String appUrl) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.appUrl = appUrl;
	}

	@Override
	public <T extends XfwForweMallResponse> T getXfwForweMallResponse(XfwForweMallRequest<T> request) {
		request.setAppKey(this.appKey);
		String signStr = null;
		Map map = null;
		TreeMap treeMap = null;

		try {
			map = MapUtil.beanToMap(request);
			treeMap = MapUtil.sortMap(map);
			signStr = SignUtil.getSignMp(JSON.toJSONString(treeMap) + this.appSecret);
		} catch (Exception var10) {
			var10.printStackTrace();
		}

		request.setSign(signStr);
		treeMap.put("sign", signStr);
		String outputStr = JSON.toJSONString(treeMap);
		String respose = HttpClientUtils.httpRequest(this.appUrl + request.getSendUrl(), "POST", outputStr, this.appKey);

		System.out.println("---------------------------" + respose);
		JSONObject json = JSONObject.parseObject(respose);

		System.out.println(json);

		String payload = json.getString("payload");
		if (!StringUtil.isEmpty(payload)) {
			JSONObject jsonObject = JSON.parseObject(payload);
			json.putAll(jsonObject);
		}
		return JSON.parseObject(JSON.toJSONString(json), request.getResponseClass());
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppUrl() {
		return this.appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
}
