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

package dev.hellpie.libs.elf.reader.types.arch64;

import com.google.common.primitives.UnsignedLong;

import java.io.IOException;
import java.nio.ByteBuffer;

import dev.hellpie.libs.elf.reader.models.ELFFile;

public final class Offset extends dev.hellpie.libs.elf.reader.types.base.Offset<UnsignedLong> {

	public Offset(ELFFile file, long offset) throws IOException {
		super(file, offset, true);
	}

	@Override
	public UnsignedLong getOffset() {
		return UnsignedLong.fromLongBits(ByteBuffer.wrap(data).getLong());
	}

	@Override
	public String toString() {
		long num = getOffset().longValue();
		return "{ dec: " + num + "; hex: 0x" + Long.toHexString(num & 0x7FFFFFFFFFFFFFFFL) + "; }";
	}

}