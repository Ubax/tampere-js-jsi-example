#pragma once

#if __has_include(<React-Codegen/AppSpecsJSI.h>) // CocoaPod headers on Apple
#include <React-Codegen/AppSpecsJSI.h>
#elif __has_include("AppSpecsJSI.h") // CMake headers on Android
#include "AppSpecsJSI.h"
#endif
#include <memory>
#include <string>

namespace facebook::react
{

    class TampereJsCppModule : public NativeSampleModuleCxxSpec<TampereJsCppModule>
    {
    public:
        TampereJsCppModule(std::shared_ptr<CallInvoker> jsInvoker);

        std::string wiki(jsi::Runtime &rt);
        double sequence(jsi::Runtime &rt, double index);
    };

} // namespace facebook::react