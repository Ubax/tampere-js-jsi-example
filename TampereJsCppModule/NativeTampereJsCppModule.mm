#include "NativeTampereJsCppModule.h"

unsigned fibonacciSequence(unsigned index)
{
    unsigned prev1 = 1, prev2 = 1;
    for (int i = 1; i < index; i++)
    {
        int b = prev1;
        prev1 = prev2 + prev1;
        prev2 = b;
    }
    return prev1;
}

namespace facebook::react
{

NativeTampereJsCppModule::NativeTampereJsCppModule(std::shared_ptr<CallInvoker> jsInvoker)
: NativeTampereJsCppModuleCxxSpec(std::move(jsInvoker)) {}

std::string fetchData(jsi::Runtime &rt){
    
        NSError *error;
        NSString *url_string = [NSString stringWithFormat: @"https://example.com/api/rest_v1/page/summary/Fibonacci_sequence"];
        NSData *data = [NSData dataWithContentsOfURL: [NSURL URLWithString:url_string]];
        if(data==NULL){
            throw jsi::JSError(rt, "Network error in wiki");
        }
        if(error==NULL){
            NSDictionary *json = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:&error];
            NSLog(@"json: %@", json);
            NSLog(@"error: %@", error);
            NSString *summary = json[@"extract"];
            NSLog(@"extract: %@", summary);
            return std::string([summary UTF8String]);
        } else {
            throw jsi::JSError(rt, "There was some error while fetching data from Wikipedia");
        }
}

jsi::String NativeTampereJsCppModule::wikiSync(jsi::Runtime &rt)
{
    return jsi::String::createFromUtf8(rt, fetchData(rt));
}
void NativeTampereJsCppModule::wikiCallback(jsi::Runtime &rt, jsi::Function onResult)
{
    onResult.call(rt, fetchData(rt));
}
jsi::Value NativeTampereJsCppModule::wikiPromise(jsi::Runtime &rt){
    return jsi::Value::undefined();
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
