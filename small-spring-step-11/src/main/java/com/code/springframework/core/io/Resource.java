package com.code.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 * Create by blacktom on 2021/08/15
 **/
public interface Resource {

	InputStream getInputStream() throws IOException;

}
