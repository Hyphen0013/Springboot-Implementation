package com.hyphen.request;

import java.util.List;

public class SingleChatRequest {
	private Long userIds;

	public SingleChatRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SingleChatRequest(Long userIds) {
		super();
		this.userIds = userIds;
	}

	public Long getUserIds() {
		return userIds;
	}

	public void setUserIds(Long userIds) {
		this.userIds = userIds;
	}

}
