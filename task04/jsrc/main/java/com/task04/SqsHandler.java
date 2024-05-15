package com.task04;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;
import com.syndicate.deployment.model.RegionScope;
import com.syndicate.deployment.model.ResourceType;
import com.syndicate.deployment.annotations.events.SqsTriggerEventSource;
import com.syndicate.deployment.annotations.resources.DependsOn;


import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "sqs_handler",
	roleName = "sqs_handler-role",
	isPublishVersion = false,
//	aliasName = "${lambdas_alias_name}",
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@SqsTriggerEventSource(
		targetQueue = "cmtr-67d6e834-demo-sqs-queue",
		batchSize = 1
)
@DependsOn(
		name = "cmtr-67d6e834-demo-sqs-queue",
		resourceType = ResourceType.SQS_QUEUE
)
public class SqsHandler implements RequestHandler<Object, String> {

	public String handleRequest(Object request, Context context) {
		System.out.println(request);
		return "";
	}
}
