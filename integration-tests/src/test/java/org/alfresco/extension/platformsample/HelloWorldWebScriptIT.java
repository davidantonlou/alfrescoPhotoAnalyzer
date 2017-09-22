/**
 * Copyright (C) 2017 Alfresco Software Limited.
 * <p/>
 * This file is part of the Alfresco SDK project.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.alfresco.extension.platformsample;

import org.junit.Test;

/**
 * Integration Test (IT) for Hello World web script.
 *
 * @author martin.bergljung@alfresco.com
 * @version 1.0
 * @since 3.0
 */
public class HelloWorldWebScriptIT {
    @Test
    public void testWebScriptCall() throws Exception {
//        String webscriptURL = "http://localhost:8080/alfresco/service/sample/helloworld";
//        String expectedResponse = "Message: 'Hello from JS!' 'HelloFromJava' aaaa";
//
//        // Login credentials for Alfresco Repo
//        CredentialsProvider provider = new BasicCredentialsProvider();
//        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "admin");
//        provider.setCredentials(AuthScope.ANY, credentials);
//
//        // Create HTTP Client with credentials
//        CloseableHttpClient httpclient = HttpClientBuilder.create()
//                .setDefaultCredentialsProvider(provider)
//                .build();
//
//        // Execute Web Script call
//        try {
//            HttpGet httpget = new HttpGet(webscriptURL);
//            HttpResponse httpResponse = httpclient.execute(httpget);
//            assertEquals("Incorrect HTTP Response Status",
//                    HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
//            HttpEntity entity = httpResponse.getEntity();
//            assertNotNull("Response from Web Script is null", entity);
//            assertEquals("Incorrect Web Script Response", expectedResponse, EntityUtils.toString(entity));
//        } finally {
//            httpclient.close();
//        }
    }
}