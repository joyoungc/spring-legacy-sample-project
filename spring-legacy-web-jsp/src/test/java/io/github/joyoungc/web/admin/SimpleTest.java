package io.github.joyoungc.web.admin;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;

import io.github.joyoungc.web.admin.model.ResponseDataIF;
import io.github.joyoungc.web.admin.model.ResponseDataIF.Intent;
import io.github.joyoungc.web.common.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTest {
	
	ObjectMapper mapper;
	String json;
	

}
