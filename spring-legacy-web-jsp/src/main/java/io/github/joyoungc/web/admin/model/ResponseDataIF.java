package io.github.joyoungc.web.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "output", "input", "intents", "entities", "context"})
@Data
public class ResponseDataIF {

	@JsonProperty("output")
	public Output output;
	@JsonProperty("input")
	public Input input;
	@JsonProperty("intents")
	public List<Intent> intents = null;
	@JsonProperty("entities")
	public List<Entity> entities = null;
	@JsonProperty("context")
	public Context context;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Data
	public static class Input {
		@JsonProperty("text")
		public String text;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Data
	public static class Intent {
		@JsonProperty("intent")
		public String intent;
		@JsonProperty("confidence")
		public Double confidence;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Data
	public static class Output {
		@JsonProperty("text")
		public List<String> text = null;
		@JsonProperty("nodes_visited")
		public List<String> nodesVisited = null;
		@JsonProperty("log_messages")
		public List<Object> logMessages = null;
		@JsonProperty("selection_policy")
		public String selectionPolicy = null;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Data
	public static class Entity {
		@JsonProperty("entity")
		public String entity;
		@JsonProperty("location")
		public List<Float> location = null;
		@JsonProperty("value")
		public String value;
		@JsonProperty("confidence")
		public Double confidence;
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Data
	public static class Context {
		@JsonProperty("conversation_id")
		public String conversationId;
		@JsonProperty("system")
		public Object system;
	}

}
