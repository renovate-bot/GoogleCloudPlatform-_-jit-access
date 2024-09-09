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

package com.google.solutions.jitaccess.util;

import com.google.common.util.concurrent.UncheckedExecutionException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class TestLazy {

  // -------------------------------------------------------------------------
  // initializeOpportunistically.
  // -------------------------------------------------------------------------

  @Test
  public void initializeOpportunistically_whenSingleThreaded() {
    final var initializations = new AtomicInteger(0);

    var lazy = Lazy.initializeOpportunistically(
      () -> {
        initializations.incrementAndGet();
        return "test";
      });

    assertEquals("test", lazy.get());
    assertEquals("test", lazy.get());
    assertEquals("test", lazy.get());

    assertEquals(1, initializations.get());
  }

  @Test
  public void initializeOpportunistically_whenInitializerFails() {
    final var initializations = new AtomicInteger(0);

    var lazy = Lazy.initializeOpportunistically(
      () -> {
        initializations.incrementAndGet();
        throw new IllegalStateException();
      });

    assertThrows(
      UncheckedExecutionException.class,
      () -> lazy.get());
    var e = assertThrows(
      UncheckedExecutionException.class,
      () -> lazy.get());

    assertInstanceOf(IllegalStateException.class, e.getCause());
    assertEquals(2, initializations.get());
  }
}