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

package dev.hellpie.libs.elf.reader.values;

import java.nio.ByteBuffer;

public enum FileType {

	TYPE_REL(1), // Relocatable file
	TYPE_EXEC(2), // Executable file
	TYPE_DYN(3), // Shared object
	TYPE_CORE(4), // Core file
	TYPE_LOOS(0xFE00), // OS specific
	TYPE_HIOS(0xFEFF), // OS specific
	TYPE_LOPROC(0xFF00), // Processor specific
	TYPE_HIPROC(0xFFFF), // Processor specific
	TYPE_UNKNOWN(0);

	public final byte[] VALUE;

	FileType(int value) {
		VALUE = ByteBuffer.allocate(2).putInt(value).array();
	}

	public static FileType get(int value) {
		switch(value) {
			case 1:
				return TYPE_REL;
			case 2:
				return TYPE_EXEC;
			case 3:
				return TYPE_DYN;
			case 4:
				return TYPE_CORE;
			case 0xFE00:
				return TYPE_LOOS;
			case 0xFEFF:
				return TYPE_HIOS;
			case 0xFF00:
				return TYPE_LOPROC;
			case 0xFFFF:
				return TYPE_HIPROC;
			default:
				return TYPE_UNKNOWN;
		}
	}
}
