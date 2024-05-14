package com.task04;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.events.SnsEventSource;
import com.syndicate.deployment.annotations.events.SqsTriggerEventSource;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.RegionScope;
import com.syndicate.deployment.model.ResourceType;
import com.syndicate.deployment.model.RetentionSetting;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "sns_handler",
    roleName = "sns_handler-role",
    isPublishVersion = false,
//	aliasName = "${lambdas_alias_name}",
    logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@SnsEventSource(
        targetTopic = "cmtr-67d6e834-demo-sns_topic",
        regionScope  = RegionScope.DEFAULT
)
@DependsOn(
        name = "cmtr-67d6e834-demo-sns_topic",
        resourceType = ResourceType.SNS_TOPIC
)
public class SnsHandler implements RequestHandler<Object, String> {

    public Map<String, Object> handleRequest(Object request, Context context) {
        System.out.println(request);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", 200);
        resultMap.put("body", "Hello from Lambda");
        return resultMap;
    }
}