<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="<tiles:getAsString name="class_ltcr" defaultValue="imethod-body imethod-ltcr"/>">
    <tiles:insertAttribute name="content_l"/>
    <div class="<tiles:getAsString name="class_tcr" defaultValue="imethod-right"/>">
        <tiles:insertAttribute name="content_t"/>
        <div class="<tiles:getAsString name="class_cr" defaultValue="imethod-bottom"/>">
            <tiles:insertAttribute name="content_c"/>
            <tiles:insertAttribute name="content_r"/>
        </div>

    </div>
</div>
