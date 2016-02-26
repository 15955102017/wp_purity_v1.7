package com.github.wp.factory.io;

import com.github.wp.factory.FactoryProvider;
import com.github.wp.system.util.io.UploadHandler;

public class UploadFileFactory implements FactoryProvider {

	@Override
	public Object produce() {
		return new UploadHandler();
	}

}
