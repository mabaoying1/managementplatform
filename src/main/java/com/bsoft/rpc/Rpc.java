package com.bsoft.rpc;

import java.util.List;
import java.util.Random;

import ctd.net.rpc.Client;
import ctd.net.rpc.balance.Balance;
import ctd.net.rpc.config.ProviderUrlConfig;
/**
 * 
 * @author Administrator
 *
 */
public class Rpc {
	
	/**
	 * RPC 调用服务
	 * @param beanName 域名
	 * @param methodName 方法名
	 * @return
	 */
	public  Object RpcInvoke(String beanName,String methodName){
		Object obj =null;
		try {
			obj = Client.rpcInvoke(beanName, methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * RPC 调用指定服务
	 * @param beanName 域名
	 * @param methodName 方法名
	 * @param ipstr 指定服务器
	 * @return
	 */
	public  Object RpcInvoke(String beanName,String methodName,String ipstr){
		Object obj =null;
		try {
			final String ipBalanceString = ipstr;
			obj = Client.rpcInvoke(beanName, methodName,new Balance() {
				private Random rd = new Random();
				@Override
				public ProviderUrlConfig select(List<ProviderUrlConfig> ls) {
					
					for(ProviderUrlConfig providerUrlConfig : ls){
						String host = providerUrlConfig.getHost();
						String ip = host.substring(0,host.indexOf(":"));
						if(ip.equals(ipBalanceString)){
							return providerUrlConfig;
						}
					}
					
					int max = ls.size();
					if (max == 0) {
				      return null;
				    }
				    if (max == 1) {
				      return (ProviderUrlConfig)ls.get(0);
				    }
				    int index = this.rd.nextInt(max);
				    return (ProviderUrlConfig)ls.get(index);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * RPC 带参数调用服务
	 * @param beanName 域名
	 * @param methodName 方法名
	 * @param parameters 参数
	 * @return
	 */
	public  Object RpcInvoke(String beanName,String methodName,Object...parameters){
		Object obj =null;
		try {
			obj = Client.rpcInvoke(beanName, methodName, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * RPC 带参数调用指定IP服务
	 * @param beanName 域名
	 * @param methodName 方法名
	 * @param ipstr 指定服务器
	 * @param parameters 参数
	 * @return
	 */
	public  Object RpcInvoke(String beanName,String methodName,String ipstr,Object...parameters){
		Object obj =null;
		try {
			final String ipBalanceString = ipstr;
			obj = Client.rpcInvoke(beanName, methodName,new Balance() {
				private Random rd = new Random();
				@Override
				public ProviderUrlConfig select(List<ProviderUrlConfig> ls) {
					
					for(ProviderUrlConfig providerUrlConfig : ls){
						String host = providerUrlConfig.getHost();
						if(host.equals(ipBalanceString)){
							return providerUrlConfig;
						}
					}
					
					int max = ls.size();
					if (max == 0) {
				      return null;
				    }
				    if (max == 1) {
				      return (ProviderUrlConfig)ls.get(0);
				    }
				    int index = this.rd.nextInt(max);
				    return (ProviderUrlConfig)ls.get(index);
				}
			}, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
