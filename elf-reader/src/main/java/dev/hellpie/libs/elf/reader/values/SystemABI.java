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

import android.util.SparseArray;

public enum SystemABI {

	ABI_HPUX(1), // Hewlett-Packard HP-UX
	ABI_NETBSD(2), // NetBSD
	ABI_GNU(3), // GNU
	ABI_LINUX(3), // Linux - Hystorical for GNU
	ABI_SOLARIS(6), // Solaris
	ABI_AIX(7), // AIX
	ABI_IRIX(8), // IRIX
	ABI_FREEBSD(9), // FreeBSD
	ABI_TRU64(10), // Compaq TRU64 UNIX
	ABI_MODESTO(11), // Novell Modesto
	ABI_OPENBSD(12), // OpenBSD
	ABI_OPENVMS(13), // OpenVMS
	ABI_NSK(14), // Hewlett-Packard Non-Stop Kernel
	ABI_AROS(15), // Amiga Research OS
	ABI_FENIXOS(16), // The FenixOS highly-scalable multi-core OS
	ABI_CLOUDABI(17), // Nuxi CloudABI
	ABI_OPENVOS(18), // Stratus Technologies OpenVOS
	ABI_RESERVED(255), // 64 to 255 are Arch-specific values
	ABI_UNKNOWN(0);

	private static SparseArray<SystemABI> abis = new SparseArray<>();

	static {
		for(SystemABI abi : SystemABI.values()) {
			abis.put(abi.VALUE, abi);
		}
	}

	public final int VALUE;

	SystemABI(int value) {
		VALUE = value;
	}

	public static SystemABI get(int value) {
		if(value >= 64 && value <= 255) return ABI_RESERVED;
		return abis.get(value, ABI_UNKNOWN);
	}
}
