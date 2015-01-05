/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sqoop.repository.postgresql;

import org.apache.sqoop.common.test.db.DatabaseProvider;
import org.apache.sqoop.common.test.db.PostgreSQLProvider;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Abstract class with convenience methods for testing postgresql repository.
 */
abstract public class PostgresqlTestCase {

  public static DatabaseProvider provider;
  public static PostgresqlTestUtils utils;
  public PostgresqlRepositoryHandler handler;

  @BeforeClass
  public static void setUpClass() {
    provider = new PostgreSQLProvider();
    utils = new PostgresqlTestUtils(provider);
  }

  @Before
  public void setUp() throws Exception {
    try {
      provider.start();
    } catch (RuntimeException e) {
      Assume.assumeTrue(false);
    }

    handler = new PostgresqlRepositoryHandler();
    handler.createOrUpgradeRepository(provider.getConnection());
  }

  @After
  public void tearDown() throws Exception {
    provider.dropSchema("sqoop");
    provider.stop();
  }
}