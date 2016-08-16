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

package dev.hellpie.libs.elf.reader.types.base;

import java.io.IOException;
import java.util.Arrays;

import dev.hellpie.libs.elf.reader.models.ELFFile;

abstract class Type {

	protected final byte[] data;

	Type(ELFFile file, long offset, int length) throws IOException {
		data = file.toBigEndian(file.read(offset, length));
	}

	public byte[] getData() {
		return data.clone();
	}

	@Override
	public abstract String toString();

	@Override
	public int hashCode() {
		return Arrays.hashCode(data);
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && this.getClass().equals(obj.getClass()) && Arrays.equals(data, ((Type) obj).data);
	}
}
