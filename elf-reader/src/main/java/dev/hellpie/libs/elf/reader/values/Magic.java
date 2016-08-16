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

public enum Magic {

	ELF_MAGIC_0(0),
	ELF_MAGIC_1(1),
	ELF_MAGIC_2(2),
	ELF_MAGIC_3(3),
	ELF_MAGIC_UNKNOWN(-1);

	public static final byte[] ELF_MAGIC = new byte[]{0x7F, 'E', 'L', 'F'};

	public final int INDEX;

	Magic(int index) {
		INDEX = index;
	}

	public static Magic get(int index) {
		switch(index) {
			case 0:
				return ELF_MAGIC_0;
			case 1:
				return ELF_MAGIC_1;
			case 2:
				return ELF_MAGIC_2;
			case 3:
				return ELF_MAGIC_3;
			default:
				return ELF_MAGIC_UNKNOWN;
		}
	}

	public static Magic get(char value) {
		switch(value) {
			case (char) 0x7F:
				return ELF_MAGIC_0;
			case 'E':
				return ELF_MAGIC_1;
			case 'L':
				return ELF_MAGIC_2;
			case 'F':
				return ELF_MAGIC_3;
			default:
				return ELF_MAGIC_UNKNOWN;
		}
	}

	public char getValue() {
		return (INDEX >= ELF_MAGIC.length || INDEX < 0 ? (char) 0x00 : (char) ELF_MAGIC[INDEX]);
	}
}
