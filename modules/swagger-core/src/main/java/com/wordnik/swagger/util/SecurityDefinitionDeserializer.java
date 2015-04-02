package com.wordnik.swagger.util;

import com.wordnik.swagger.models.auth.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class SecurityDefinitionDeserializer extends JsonDeserializer<SecuritySchemeDefinition> {
  @Override
  public SecuritySchemeDefinition deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    SecuritySchemeDefinition result = null;

    JsonNode node = jp.getCodec().readTree(jp);
    JsonNode inNode = node.get("type");

    if (inNode != null) {
      String type = inNode.asText();
      if ("basic".equals(type)) {
        result = Json.mapper().convertValue(node, BasicAuthDefinition.class);
      } else if ("apiKey".equals(type)) {
        result = Json.mapper().convertValue(node, ApiKeyAuthDefinition.class);
      } else if ("oauth2".equals(type)) {
        result = Json.mapper().convertValue(node, OAuth2Definition.class);
      }
    }

    return result;
  }
}
