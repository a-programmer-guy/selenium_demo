package listener_demo;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName()+" test case started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("The name of the testcase passed is :"+ result.getName());	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("The name of the testcase failed is :" + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("The name of the testcase Skipped is :"+ result.getName());	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}
