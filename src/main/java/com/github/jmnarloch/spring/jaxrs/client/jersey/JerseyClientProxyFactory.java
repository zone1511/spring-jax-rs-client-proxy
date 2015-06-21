/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jmnarloch.spring.jaxrs.client.jersey;

import com.github.jmnarloch.spring.jaxrs.client.support.JaxRsClientProxyFactory;
import com.github.jmnarloch.spring.jaxrs.client.support.JaxRsClientProxyFactorySupport;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * The Jersey {@link JaxRsClientProxyFactory} implementation.
 * It delegates the execution to {@link WebResourceFactory} create the proxy instances.
 *
 * @author Jakub Narloch
 */
class JerseyClientProxyFactory extends JaxRsClientProxyFactorySupport {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T createClientProxy(Class<T> serviceClass, String serviceUrl) {

        final Client client = ClientBuilder.newClient();
        registerProviders(client);
        final WebTarget target = client.target(serviceUrl);
        return WebResourceFactory.newResource(serviceClass, target);
    }

    /**
     * Registers the provider classes.
     *
     * @param client the client
     */
    private void registerProviders(Client client) {
        for(Class<?> provider : getProviders()) {
            client.register(provider);
        }
    }
}
