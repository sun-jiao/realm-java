/*
 * Copyright 2020 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.realm.log.obfuscator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Obfuscator for custom function-related login requests. It will replace all function arguments
 * that appear before {@code "options":"***"} with {@code "functionArgs":"***"}.
 */
public class CustomFunctionObfuscator extends PatternObfuscator {

    private CustomFunctionObfuscator(Map<Pattern, String> patternReplacementMap) {
        super(patternReplacementMap);
    }

    /**
     * Creates a {@link PatternObfuscator} for custom functions.
     *
     * @return an obfuscator that keeps custom function information from being displayed in the
     * logcat.
     */
    public static CustomFunctionObfuscator obfuscator() {
        return new CustomFunctionObfuscator(getPatterns());
    }

    private static Map<Pattern, String> getPatterns() {
        Map<Pattern, String> map = new HashMap<>();
        map.put(Pattern.compile("\\{(.+?),\"options\":"), "{\"functionArgs\":\"***\",\"options\":");
        return map;
    }
}