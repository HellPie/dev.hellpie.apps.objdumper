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

public enum Endianness {

	ENDIAN_LITTLE(1),
	ENDIAN_BIG(2),
	ENDIAN_UNKNOWN(0);

	public final int VALUE;

	Endianness(int value) {
		VALUE = value;
	}

	public static Endianness get(int value) {
		switch(value) {
			case 1:
				return ENDIAN_LITTLE;
			case 2:
				return ENDIAN_BIG;
			default:
				return ENDIAN_UNKNOWN;
		}
	}
}
