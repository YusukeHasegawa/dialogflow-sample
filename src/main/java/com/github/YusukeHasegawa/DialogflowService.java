package com.github.YusukeHasegawa;

import org.springframework.stereotype.Service;

import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

@Service
public class DialogflowService {

	private AIDataService dataService;

	public DialogflowService(AIDataService dataService) {
		this.dataService = dataService;
	}

	public AIResponse request(String query) throws AIServiceException {
		AIRequest request = new AIRequest(query);
		AIResponse response = dataService.request(request);
		return response;
	}
}
