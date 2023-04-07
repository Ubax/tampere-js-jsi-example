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

    TampereJsCppModule::TampereJsCppModule(std::shared_ptr<CallInvoker> jsInvoker)
        : TampereJsCppModuleCxxSpec(std::move(jsInvoker)) {}

    std::string TampereJsCppModule::wiki(jsi::Runtime &rt)
    {
        return R"V0G0N(
             Fibonacci, also known as Leonardo Bonacci, Leonardo of Pisa, or Leonardo Bigollo Pisano, 
             was an Italian mathematician from the Republic of Pisa, 
             considered to be 'the most talented Western mathematician of the Middle Ages'.)V0G0N";
    }
    double TampereJsCppModule::sequence(jsi::Runtime &rt, double index)
    {
        if (index > 0)
        {
            return 1;
        }
        return 0;
    }

} // namespace facebook::react