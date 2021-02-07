package de.vilkas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import de.vilkas.controller.AWSCrossAccountAccessController;
import de.vilkas.model.InputData;
import org.apache.log4j.Logger;

import java.util.Map;

public class LambdaHandler implements RequestHandler<Map<String, Object>, String> {

	private static final Logger LOG = Logger.getLogger(LambdaHandler.class);

	@Override
	public String handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: " + input);
		var name = String.valueOf(input.get("name"));
		var policy = new Gson().toJson(input.get("policy"));
		return new AWSCrossAccountAccessController()
				.create(new InputData(name, policy));
	}
}
