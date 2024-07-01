package ui.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerClass extends BaseTest implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (isUITest(result)) {
			captureScreenShot(result.getMethod().getMethodName() + ".png");
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (isUITest(result)) {
			captureScreenShot(result.getMethod().getMethodName() + ".png");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	private boolean isUITest(ITestResult result) {
		// Example: Check if the test method belongs to UI testing
		return result.getMethod().getMethodName().startsWith("uiTest");
	}

}
