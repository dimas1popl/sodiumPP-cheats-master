package com.dimsteams.sodiumpp.scripting.monaco;

import com.dimsteams.scripting.compiler.CompilationParameters;

public interface CompilationParametersResolver {
    CompilationParameters resolve(String type);
}