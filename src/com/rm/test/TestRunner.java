package com.rm.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		
		Result result = JUnitCore.runClasses(TripletTest.class, TripletDecoderTest.class);

		System.out.println();
		if (!result.getFailures().isEmpty()) {
			System.out.println("Failed Test Cases:");
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
			System.out.println();
		}

		int total = result.getRunCount();
		int failed = result.getFailureCount();
		int ignored = result.getIgnoreCount();
		int succeed = total - (failed + ignored);
		
		System.out.println("Tests found : "+total+"; Successful : "+succeed+"; Ignored : "+ignored+"; Failed : "+failed);
	}
}
