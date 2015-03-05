package com.heidsoft.aliyun.simple;

import com.aliyun.api.AliyunClient;
import com.aliyun.api.DefaultAliyunClient;
import com.aliyun.api.ecs.ecs20140526.request.CreateInstanceRequest;
import com.aliyun.api.ecs.ecs20140526.request.DeleteInstanceRequest;
import com.aliyun.api.ecs.ecs20140526.request.DescribeInstanceAttributeRequest;
import com.aliyun.api.ecs.ecs20140526.response.CreateInstanceResponse;
import com.aliyun.api.ecs.ecs20140526.response.DeleteInstanceResponse;
import com.aliyun.api.ecs.ecs20140526.response.DescribeInstanceAttributeResponse;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.StringUtils;

public class AliyunSimple {
	
	 private static AliyunClient client;
	    static {
	        String serverUrl="<serverUrl>";//例如：http://ecs.aliyuncs.com/
	        String accessKeyId="<accessKeyId>";
	        String accessKeySecret="<accessKeySecret>";
	        
	        //// 初始化一个AliyunClient
	        client = new DefaultAliyunClient(serverUrl, accessKeyId, accessKeySecret);
	    }


	public static void main(String[] args) {
		
	    
	}
	
	/**
	 * 创建实例
	 */
	public void createInstance() {
		CreateInstanceRequest createInstanceRequest = new CreateInstanceRequest();
		createInstanceRequest.setRegionId("<RegionId>");
		createInstanceRequest.setImageId("<ImageId>");
		createInstanceRequest.setInstanceType("<InstanceType>");
		createInstanceRequest.setSecurityGroupId("<SecurityGroupId>");

		try {
			CreateInstanceResponse createInstanceResponse = client.execute(createInstanceRequest);
			if (StringUtils.isEmpty(createInstanceResponse.getErrorCode())) {//创建成功
				String instanceId =	createInstanceResponse.getInstanceId();//取得实例ID
			}else {//创建失败
				String errorCode = createInstanceResponse.getErrorCode();//取得错误码
				String message = createInstanceResponse.getMessage();//取得错误信息
			}
		} catch (ApiException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 查询实例信息
	 */
	public void describeInstanceAttribute(String instanceId) {
			DescribeInstanceAttributeRequest describeInstanceAttributeRequest=new DescribeInstanceAttributeRequest();
			describeInstanceAttributeRequest.setInstanceId(instanceId);
			try {	
			DescribeInstanceAttributeResponse describeInstanceAttributeResponse=client.execute(describeInstanceAttributeRequest);
			if (StringUtils.isEmpty(describeInstanceAttributeResponse.getErrorCode())) {//查询成功
				//查看实例信息相关代码
				//......
			}else {//查询失败
				String errorCode = describeInstanceAttributeResponse.getErrorCode();//取得错误码
				String message = describeInstanceAttributeResponse.getMessage();//取得错误信息
			}
			} catch (ApiException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 删除实例
	 */
	public void deleteInstance(String instanceId) {
		DeleteInstanceRequest deleteInstanceRequest =new DeleteInstanceRequest();
		deleteInstanceRequest.setInstanceId(instanceId);
		try {
			DeleteInstanceResponse deleteInstanceResponse=client.execute(deleteInstanceRequest);
			if (StringUtils.isEmpty(deleteInstanceResponse.getErrorCode())) {//删除成功
				
			}else {//删除失败
				String errorCode = deleteInstanceResponse.getErrorCode();//取得错误码
				String message = deleteInstanceResponse.getMessage();//取得错误信息
			}
		} catch (ApiException e) {
			// TODO: handle exception
		}
	}

}
