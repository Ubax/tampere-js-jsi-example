#pragma once

#if __has_include(<React-Codegen/TampereJsSpecsJSI.h>) // CocoaPod headers on Apple
#include <React-Codegen/TampereJsSpecsJSI.h>
#elif __has_include("TampereJsSpecsJSI.h") // CMake headers on Android
#include "TampereJsSpecsJSI.h"
#endif
#include <memory>
#include <string>
#include <optional>

namespace facebook::react
{

    class NativeTampereJsCppModule : public NativeTampereJsCppModuleCxxSpec<NativeTampereJsCppModule>
    {
    public:
        NativeTampereJsCppModule(std::shared_ptr<CallInvoker> jsInvoker);

        jsi::String wiki(jsi::Runtime &rt);
        double sequence(jsi::Runtime &rt, double index);
    };

} // namespace facebook::react