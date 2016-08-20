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

package dev.hellpie.libs.elf.reader.data;

import com.google.common.base.MoreObjects;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import dev.hellpie.libs.elf.reader.models.ELFFile;
import dev.hellpie.libs.elf.reader.models.ELFParsingException;
import dev.hellpie.libs.elf.reader.types.base.UnsignedChar;
import dev.hellpie.libs.elf.reader.values.ArchClass;
import dev.hellpie.libs.elf.reader.values.Endianness;
import dev.hellpie.libs.elf.reader.values.Magic;
import dev.hellpie.libs.elf.reader.values.SystemABI;
import dev.hellpie.libs.elf.reader.values.Version;

public final class IdentHeader implements Cloneable {

	public static final int HEADER_LENGTH = 16;
	public static final int PADDING_START = 9;

	private final byte[] data = new byte[HEADER_LENGTH];

	private final Magic[] magic = new Magic[Magic.ELF_MAGIC.length];
	private final ArchClass arch;
	private final Endianness endianness;
	private final Version version;
	private final SystemABI abi;
	private final UnsignedChar abiVersion;
	private final UnsignedChar[] pad = new UnsignedChar[HEADER_LENGTH - PADDING_START];

	private IdentHeader(IdentHeader clone) {
		System.arraycopy(clone.data, 0, this.data, 0, data.length);
		System.arraycopy(clone.magic, 0, this.magic, 0, magic.length);
		this.arch = clone.arch;
		this.endianness = clone.endianness;
		this.version = clone.version;
		this.abi = clone.abi;
		this.abiVersion = clone.abiVersion;
		System.arraycopy(clone.pad, 0, this.pad, 0, pad.length);
	}

	public IdentHeader(ELFFile file) throws IOException, ELFParsingException {

		int index;

		// Magic checking
		byte[] magicData = file.read(0, Magic.ELF_MAGIC.length);
		for(index = 0; index < Magic.ELF_MAGIC.length; index++) {
			data[index] = magicData[index]; // Register data
			if(magicData[index] == Magic.ELF_MAGIC[index]) {
				magic[index] = Magic.get(index);
			} else {
				throw new ELFParsingException("IdentHeader: At byte '" + index + "': Expected '" + Magic.ELF_MAGIC[index] + "' but got '" + magicData[index] + "'");
			}
		}

		// Read arch
		byte[] archData = file.read(++index, 1);
		arch = ArchClass.get(ByteBuffer.wrap(archData).getInt());
		if(archData.length == 1 && arch != ArchClass.CLASS_ARCH_UNKNOWN) {
			data[index] = archData[0];
		} else {
			throw new ELFParsingException("IdentHeader: At byte '" + index + "': Got invalid ArchClass '" + ByteBuffer.wrap(archData).getInt() + "'");
		}

		// Read endianness
		byte[] endianData = file.read(++index, 1);
		endianness = Endianness.get(ByteBuffer.wrap(endianData).getInt());
		if(endianData.length == 1 && endianness != Endianness.ENDIAN_UNKNOWN) {
			data[index] = endianData[0];
		} else {
			throw new ELFParsingException("IdentHeader: At byte '" + index + "': Got invalid Endianness '" + ByteBuffer.wrap(endianData).getInt() + "'");
		}

		// Read ELF version, can only be '1'
		byte[] versionData = file.read(++index, 1);
		version = Version.get(ByteBuffer.wrap(versionData).getInt());
		if(versionData.length == 1 && version != Version.VER_UNKNOWN) {
			data[index] = versionData[0];
		} else {
			throw new ELFParsingException("IdentHeader: At byte '" + index + "': Got invalid ELF Version '" + ByteBuffer.wrap(versionData).getInt() + "'");
		}

		// Read System ABI
		byte[] abiData = file.read(++index, 1);
		abi = SystemABI.get(ByteBuffer.wrap(abiData).getInt());
		if(abiData.length == 1 && abi != SystemABI.ABI_UNKNOWN) {
			data[index] = abiData[0];
		} else {
			throw new ELFParsingException("IdentHeader: At byte '" + index + "': Got invalid System ABI '" + ByteBuffer.wrap(abiData).getInt() + "'");
		}

		// Read ABI Version, should always be 0 (?), but does not matter
		if(arch == ArchClass.CLASS_ARCH32) {
			abiVersion = new dev.hellpie.libs.elf.reader.types.arch32.UnsignedChar(file, ++index);
			data[index] = abiVersion.getData()[0];
		} else {
			abiVersion = new dev.hellpie.libs.elf.reader.types.arch64.UnsignedChar(file, ++index);
			data[index] = abiVersion.getData()[0];
		}

		// Padding bytes have no meaningful value
		while(index < HEADER_LENGTH) {
			UnsignedChar currPad;
			if(arch == ArchClass.CLASS_ARCH32) {
				currPad = new dev.hellpie.libs.elf.reader.types.arch32.UnsignedChar(file, ++index);
			} else {
				currPad = new dev.hellpie.libs.elf.reader.types.arch64.UnsignedChar(file, ++index);
			}
			data[index] = currPad.getData()[0];
			pad[index - PADDING_START] = currPad;
		}
	}

	@Override
	public IdentHeader clone() {
		try {
			super.clone();
		} catch(CloneNotSupportedException ignored) { /*...*/ }
		return new IdentHeader(this);
	}

	public byte[] getData() {
		return data.clone();
	}

	public Magic[] getMagic() {
		return magic.clone();
	}

	public ArchClass getArchClass() {
		return arch;
	}

	public Endianness getEndianness() {
		return endianness;
	}

	public Version getVersion() {
		return version;
	}

	public SystemABI getSystemAbi() {
		return abi;
	}

	public UnsignedChar getAbiVersion() {
		return abiVersion;
	}

	public UnsignedChar[] getPadding() {
		return pad.clone();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("data", data)
				.add("magic", magic)
				.add("arch", arch)
				.add("endianness", endianness)
				.add("version", version)
				.add("abi", abi)
				.add("abiVersion", abiVersion)
				.add("pad", pad)
				.toString();
	}

	@SuppressWarnings("SimplifiableIfStatement")
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		return Arrays.equals(data, ((IdentHeader) o).data);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(data);
	}
}
