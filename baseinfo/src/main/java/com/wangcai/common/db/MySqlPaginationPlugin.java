package com.wangcai.common.db;  
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
  
/** 
 *@描述: 分页插件
 */  
public class MySqlPaginationPlugin extends PluginAdapter {  
    @Override  
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,  
            IntrospectedTable introspectedTable) {  
        // add field, getter, setter for limit clause  
        addLimit(topLevelClass, introspectedTable, "page");  
        return super.modelExampleClassGenerated(topLevelClass,  
                introspectedTable);  
    }  
    @Override  
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(  
            XmlElement element, IntrospectedTable introspectedTable) {  
        XmlElement isNotNullElement = new XmlElement("if"); 
        isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitStart>=0"));
        isNotNullElement.addElement(new TextElement("limit #{limitStart} , #{limitEnd}"));  
        element.addElement(isNotNullElement);  
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,  
                introspectedTable);  
    }  
    private void addLimit(TopLevelClass topLevelClass,  
            IntrospectedTable introspectedTable, String name) {  
        CommentGenerator commentGenerator = context.getCommentGenerator();  
        topLevelClass.addImportedType("com.wangcai.common.web.Page");
        Field field = new Field();  
        field.setVisibility(JavaVisibility.PROTECTED);  
//      field.setType(FullyQualifiedJavaType.getIntInstance());  
        FullyQualifiedJavaType ttype = new FullyQualifiedJavaType("com.wangcai.common.web.Page");
        field.setType(ttype);  
        field.setName(name);  
//      field.setInitializationString("-1");  
        commentGenerator.addFieldComment(field, introspectedTable);  
        topLevelClass.addField(field);  
        char c = name.charAt(0);  
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();  
        method.setVisibility(JavaVisibility.PUBLIC);  
        method.setName("set" + camel);  
        method.addParameter(new Parameter(ttype, name));  
        method.addBodyLine("this." + name + "=" + name + ";");  
        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
        topLevelClass.addMethod(method);  
        
        method = new Method();  
        method.setVisibility(JavaVisibility.PUBLIC);  
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());  
        method.setName("getLimitStart");  
        method.addBodyLine("if(this.page==null) {return -1;} else {return this.page.getLimitStart();}");  
        
        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
        topLevelClass.addMethod(method);
        
        method = new Method();  
        method.setVisibility(JavaVisibility.PUBLIC);  
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());  
        method.setName("getLimitEnd");  
        method.addBodyLine("if(this.page==null) {return -1;} else {return this.page.getLimitEnd();}");  
        
        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
        topLevelClass.addMethod(method);  
    }  
    /** 
     * This plugin is always valid - no properties are required 
     */  
    public boolean validate(List<String> warnings) {  
        return true;  
    }  
  
}  