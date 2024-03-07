<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Thêm tòa nhà</title>
</head>
<body>
<div class="main-content" >


    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">

                <div class="page-header">
                    <h1 >
                        Sửa hoặc thêm mới tòa nhà
                    </h1>
                </div><!-- /.page-header -->
                <div class = "row" style ="font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;">
                    <form:form modelAttribute="buildingEdit" id ="listForm" method="GET">
                        <div class="col-xs-12">
                            <form class ="form-horizontal" role ="form" id ="form-edit">
                                <di class ="form-group">
                                    <label class ="col-xs-3">Tên tòa nhà</label>
                                    <div class = "col-xs-9">

                                        <form:input class ="form-control" path="name"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label  class ="col-xs-3">Quận</label>
                                    <div class ="col-xs-2">
                                        <form:select  class ="form-control" path ="district">
                                            <form:option value ="">--- Chọn quận ---</form:option>
                                            <form:options  items ="${districts}"/>
                                        </form:select>

                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Phường</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="ward"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Đường</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="street"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Kết cấu </label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="structure"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Số tầng hầm</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="numberOfBasement"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Diện tích sàn</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="floorArea"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Hướng </label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="direction"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Hạng</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="level"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Diện tích thuê</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="rentArea"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Giá thuê</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="rentprice"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Mô tả giá </label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="rentpricedescription"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Phí dịch vụ</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="serviceFee"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Phí ô tô</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="carFee"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Phí mô tô</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="motoFee"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Phí ngoài giờ</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="overtimeFee"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Tiền điện </label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="electricityFee"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Đặt cọc </label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="deposit"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Thanh toán</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="payment"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Thời gian thuê </label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="rentTime"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Thời gian trang trí </label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="decorationTime"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Tên quản lí</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="managerName"/>
                                    </div>
                                </di>


                                <di class ="form-group">
                                    <label class ="col-xs-3">SDT quản lí </label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="managerPhone"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Phí môi giới</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="brokerageFee"/>
                                    </div>
                                </di>




                                <di class ="form-group">
                                    <label class ="col-xs-3">Loại tòa nhà</label>
                                    <div class = "col-xs-9">
                                        <form:checkboxes path="typeCode" items="${listType}" />
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3">Ghi chú</label>
                                    <div class = "col-xs-9">
                                        <form:input class ="form-control" path="note"/>
                                    </div>
                                </di>

                                <di class ="form-group">
                                    <label class ="col-xs-3"></label>
                                    <div class ="col-xs-9">

                                        <c:if test="${not empty buildingEdit.id}">
                                            <button type ="button" class =" btn btn-infor" id ="btnAddOrUpdateBuilding" title="Cập nhật tòa nhà">
                                                Cập nhật tòa nhà
                                            </button>
                                            <button  type ="button" class =" btn btn-infor" id ="btnCancel" title ="Hủy thao tác">
                                                Hủy thao tác
                                            </button>
                                        </c:if>
                                        <c:if test="${empty buildingEdit.id}">
                                            <button type ="button" class =" btn btn-infor" id ="btnAddOrUpdateBuilding" title="Thêm tòa nhà">
                                                Thêm tòa nhà
                                            </button>
                                            <button  type ="button" class =" btn btn-infor" id ="btnCancel" title ="Hủy thao tác">
                                                Hủy thao tác
                                            </button>
                                        </c:if>

                                    </div>
                                </di>
                                <form:hidden path="id" id="buildingId"/>


                            </form>

                        </div>

                    </form:form>

                </div>


            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->







</div><!-- /.main-container -->


<script>
    $('#btnAddOrUpdateBuilding').click(function(){
        var data ={};
        var typeCode =[];
        var formdata = $('#listForm').serializeArray();
        $.each(formdata, function(i,v){
            if(v.name != 'typeCode'){
                data["" + v.name +""] = v.value;
            }
            else {
                typeCode.push(v.value);
            }
        });
        data['typeCode'] =typeCode;
        if(typeCode != ""){
           AddOrUpdate(data);
        }
        else {
            window.location.href = "/admin/building-edit?typeCode=require";

        }


    });


    function AddOrUpdate (data){
        // call api
        $.ajax({
            type: "POST",
            url : "/admin/building",
            data : JSON.stringify(data),
            contentType : "application/json",
            dataType : "JSON",
            success : function(response){
                window.location.href = "/admin/building-list?message=error";
                console.log("OK");
            },
            error : function(response){
                console.log("fail");
                window.location.href = "/admin/building-list";
                console.log(response);
            }
        });
    };

    $('#btnCancel').click(function (){
        window.location.href ="admin/building-list";
    });
</script>

</body>
</html>
