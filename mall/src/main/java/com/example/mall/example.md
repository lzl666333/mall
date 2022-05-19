public static void main(String[] args) throws ClientException {
iterateAddress(1L);
}

	private static void iterateAddress(Long parentId) throws ClientException {
		List<Address> list = getList(parentId);
		if (list.isEmpty()) {
			return;
		}
		for (Address subAddress : list) {
			System.out.println(subAddress.getCode() + "," + subAddress.getName());
			iterateAddress(subAddress.getCode());
		}
	}

	private static List<Address> getList(Long parentId) throws ClientException {
		QueryAddressListRequest request = new QueryAddressListRequest();
		request.setParentId(String.valueOf(parentId));
		QueryAddressListResponse response = MallSdkClient.acsClient().getXfwForweMallResponse(request);
		if (response.isSuccess()) {
			return response.getAddresses();
		}
		return null;
	}
