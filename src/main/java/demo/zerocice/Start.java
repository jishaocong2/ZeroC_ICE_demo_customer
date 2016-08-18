package demo.zerocice;

import demo.zerocice.service.DemoPrx;
import demo.zerocice.service.DemoPrxHelper;

public class Start {
	public static void main(String[] args) {
		int status = 0;
		// Communicator实例
		Ice.Communicator ic = null;
		try {
			// 调用Ice.Util.Initialize()初始化Ice run time
			ic = Ice.Util.initialize(args);

			// 获取远地打印机的代理
			Ice.ObjectPrx base = ic.stringToProxy("Demo:default -p 9999");

			// 将上面的代理向下转换成一个Printer接口的代理
			DemoPrx demo = DemoPrxHelper.checkedCast(base);

			// 如果转换成功
			if (demo == null) {
				throw new Error("Invalid proxy");
			}

			// 调用这个代理，将字符串传给它
			demo.print("成功了耶！");

		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		} finally {
			if (ic != null) {
				ic.destroy();
			}
		}
		System.exit(status);
	}
}
