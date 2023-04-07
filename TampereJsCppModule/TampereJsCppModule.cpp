#include "TampereJsCppModule.h"

unsigned fibonacciSequence(unsigned index)
{
    unsigned prev1 = 1, prev2 = 1;
    for (int i = 1; i <= index; i++)
    {
        int b = prev2;
        prev2 = prev2 + prev1;
        prev1 = b;
    }
    return prev2;
}

namespace facebook::react
{

    NativeTampereJsCppModule::NativeTampereJsCppModule(std::shared_ptr<CallInvoker> jsInvoker)
        : NativeTampereJsCppModuleCxxSpec(std::move(jsInvoker)) {}

    jsi::String NativeTampereJsCppModule::wiki(jsi::Runtime &rt)
    {
        return jsi::String::createFromUtf8(rt, std::string(R"V0G0N(
             Fibonacci, also known as Leonardo Bonacci, Leonardo of Pisa, or Leonardo Bigollo Pisano, 
             was an Italian mathematician from the Republic of Pisa, 
             considered to be 'the most talented Western mathematician of the Middle Ages'.)V0G0N"));
    }
    double NativeTampereJsCppModule::sequence(jsi::Runtime &rt, double index)
    {
        if (index > 0)
        {
            return fibonacciSequence((unsigned)index);
        }
        return -1;
    }

} // namespace facebook::react