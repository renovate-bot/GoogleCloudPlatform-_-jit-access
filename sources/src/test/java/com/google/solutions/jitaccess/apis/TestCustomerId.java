//
// Copyright 2024 Google LLC
//
// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
//

package com.google.solutions.jitaccess.apis;

import com.google.solutions.jitaccess.TestRecord;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomerId extends TestRecord<CustomerId> {

  @Override
  protected @NotNull CustomerId createInstance() {
    return new CustomerId("C1");
  }

  @Override
  protected @NotNull CustomerId createDifferentInstance() {
    return new CustomerId("C2");
  }

  // -------------------------------------------------------------------------
  // Constructor.
  // -------------------------------------------------------------------------

  @Test
  public void constructor_whenMalformed() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new CustomerId("123"));
  }

  // -------------------------------------------------------------------------
  // toString.
  // -------------------------------------------------------------------------

  @Test
  public void toString_returnsId() {
    assertEquals(
      "C123",
      new CustomerId("C123").toString());
  }
}
