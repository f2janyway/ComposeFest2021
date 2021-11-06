/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelabs.state.todo

import com.codelabs.state.util.generateRandomTodoItem
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class TodoViewModelTest {
    lateinit var viewModel: TodoViewModel
    lateinit var item1: TodoItem
    lateinit var item2: TodoItem

    @Before
    fun setup() {
        viewModel = TodoViewModel()
        item1 = generateRandomTodoItem()
        item2 = generateRandomTodoItem()
        viewModel.addItem(item1)
        viewModel.addItem(item2)
    }

    @Test
    fun whenRemovingItem_updateList() {

        viewModel.removeItem(item1)

        assertThat(viewModel.todoItems).isEqualTo(listOf(item2))
        assertThat(viewModel.currentEditItem).isNull()
    }

    @Test
    fun selectItem_updateCurrentItem() {

        viewModel.onEditItemSelected(item1)
        assertThat(viewModel.currentEditItem).isEqualTo(item1)

        viewModel.onEditItemSelected(item2)
        assertThat(viewModel.currentEditItem).isEqualTo(item2)
    }
}
