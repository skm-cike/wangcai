package com.wangcai.common.web.easyui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.wangcai.common.util.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * @corporation Enstrong S&T
 * @author jingpj
 * @date Jun 19, 2009
 * @path com.est.common.ext.util.frontdatautil
 * @name com.est.common.ext.util.frontdatautil.EditableGridDataHelper
 * @description 可编辑grid前台返回数据转换为Bean帮助类
 */
public class EditableGridDataHelper<T> {
	
	private ArrayList<T> objects;
	public EditableGridDataHelper(){
		objects = new ArrayList();
	}
    private JsonGenerator jsonGenerator = null;
    private ObjectMapper objectMapper = null;

	public ArrayList<T> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<T> objects) {
		this.objects = objects;
	}

    private void init() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        if (jsonGenerator == null) {
            try {
                jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	@SuppressWarnings("unchecked")
	public static EditableGridDataHelper data2bean(String dataStr , Class<?> clazz){
		EditableGridDataHelper gridData = new EditableGridDataHelper();
		JSONArray jsonArr = JSONArray.fromObject(dataStr);

		DateMorpher dateMorpher = new DateMorpher(new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH","yyyy-MM-dd HH:mm","yyyy-MM-dd"});
		dateMorpher.setUseDefault(true);
		JSONUtils.getMorpherRegistry().registerMorpher(dateMorpher);

		Iterator<JSONObject> iterator = jsonArr.iterator();
		while(iterator.hasNext()){
			JSONObject jsonObj = iterator.next();
			gridData.objects.add(JSONObject.toBean(jsonObj, clazz));
		}
		
		return gridData;
	}

}
