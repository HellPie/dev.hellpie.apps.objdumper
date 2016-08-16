/*
 * Copyright 2016 Diego Rossi (@_HellPie)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.hellpie.libs.elf.reader.types.arch32;

import java.io.IOException;

import dev.hellpie.libs.elf.reader.models.ELFFile;

public final class SignedWord extends dev.hellpie.libs.elf.reader.types.base.SignedWord {

	public SignedWord(ELFFile file, long offset) throws IOException {
		super(file, offset);
	}
}
