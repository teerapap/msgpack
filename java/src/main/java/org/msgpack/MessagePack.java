//
// MessagePack for Java
//
// Copyright (C) 2009-2011 FURUHASHI Sadayuki
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
package org.msgpack;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MessagePack {

	public static byte[] pack(Object obj, Template tmpl) throws MessageTypeException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		try {
			new Packer(out).pack(obj, tmpl);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return out.toByteArray();
	}

	public static void pack(OutputStream out, Object obj, Template tmpl) throws IOException, MessageTypeException {
		new Packer(out).pack(obj, tmpl);
	}


	public static MessagePackObject unpack(byte[] buffer) throws MessageTypeException {
		Unpacker pac = new Unpacker();
		pac.wrap(buffer);
		try {
			return pac.unpackObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object unpack(byte[] buffer, Template tmpl) throws MessageTypeException {
		Unpacker pac = new Unpacker();
		pac.wrap(buffer);
		try {
			return pac.unpack(tmpl);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T unpack(byte[] buffer, Template tmpl, T to) throws MessageTypeException {
		Unpacker pac = new Unpacker();
		pac.wrap(buffer);
		try {
			return pac.unpack(tmpl, to);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static MessagePackObject unpack(InputStream in) throws IOException {
		Unpacker pac = new Unpacker(in);
		return pac.unpackObject();
	}

	public static Object unpack(InputStream in, Template tmpl) throws IOException, MessageTypeException {
		Unpacker pac = new Unpacker(in);
		try {
			return pac.unpack(tmpl);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T unpack(InputStream in, Template tmpl, T to) throws IOException, MessageTypeException {
		Unpacker pac = new Unpacker(in);
		try {
			return pac.unpack(tmpl, to);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

