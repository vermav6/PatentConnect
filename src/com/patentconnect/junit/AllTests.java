package com.patentconnect.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ParserTest.class,
	GooglePatentsTest.class,
	StatusTest.class,
	CitationRecordTest.class,
	ImageLoaderTest.class
	
})
public class AllTests {

}
