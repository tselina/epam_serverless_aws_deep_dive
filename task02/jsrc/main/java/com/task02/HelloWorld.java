package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.lambda.LambdaUrlConfig;
import com.syndicate.deployment.model.RetentionSetting;
import com.syndicate.deployment.model.lambda.url.AuthType;
import com.syndicate.deployment.model.lambda.url.InvokeMode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "hello_world",
	roleName = "hello_world-role",
//	isPublishVersion = true,
//aliasName = "${lambdas_alias_name}",
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@LambdaUrlConfig(
	authType = AuthType.NONE,
	invokeMode = InvokeMode.BUFFERED
)
//@LambdaConsurrency()
public class HelloWorld implements RequestHandler<Map<String, Object>, Map<String, Object>> {

	public Map<String, Object> handleRequest(Map<String, Object> request, Context context) {
		LambdaLogger logger = context.getLogger();
//		System.out.println("Hello from lambda");
		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("statusCode", 200);
//		resultMap.put("message", "Hello from Lambda");
//		resultMap.put("statusCode", "200");
		logger.log(request.toString());
//		resultMap.put("request", request.toString());
//		resultMap.put("requestFromRequest class:", request.getClass().getName());
		String rawPath = "";
		try {
			for (Map.Entry<String, Object> entry: request.entrySet()) {
				logger.log("key:" + entry.getKey() + "|||value:" + entry.getValue());
			}
//			resultMap.put("requestFromRequest", request.toString());
			rawPath = request.get("rawPath").toString();
//			resultMap.put("rawPath", rawPath);
//			resultMap.put("rawQueryString", request.get("rawQueryString").toString());
//			resultMap.put("body", "Some response");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
		if (rawPath.equals("/hello")) {
			resultMap.put("body", "{\"statusCode\": 200, \"message\": \"Hello from Lambda\"}");
		} else {
			resultMap.put("body", "{\"statusCode\": 400, \"message\": \"Bad request syntax or unsupported method. Request path: " + rawPath + ". HTTP method: GET\"}");
		}
		return resultMap;
	}
}
