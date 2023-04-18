/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, {useEffect, useState} from 'react';
import type {PropsWithChildren} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TextInput,
  useColorScheme,
  View,
} from 'react-native';

import {Colors, Header} from 'react-native/Libraries/NewAppScreen';
import NativeTampereJsModule from './TampereJsCppModule/NativeTampereJsCppModule';

type SectionProps = PropsWithChildren<{
  title: string;
}>;

function Section({children, title}: SectionProps): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';
  return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
        {children}
      </Text>
    </View>
  );
}

function fibonacci(index: number) {
  let prev1 = 1,
    prev2 = 1;
  for (let i = 1; i < index; i++) {
    const b = prev2;
    prev2 = prev1;
    prev1 = b + prev1;
  }
  return prev1;
}

declare const performance: {now: () => number};

function now() {
  return performance.now();
}

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const [fibonacciInput, setFibonacciInput] = useState(2);
  const [fibonacciResultCpp, setFibonacciResultCpp] = useState(0);
  const [timeCpp, setTimeCpp] = useState(0);
  const [fibonacciResultJs, setFibonacciResultJs] = useState(0);
  const [timeJs, setTimeJs] = useState(0);
  const [wikiContent, setWiki] = useState('Loading...');

  useEffect(() => {
    try {
      setWiki(NativeTampereJsModule.wiki());
    } catch (e) {
      setWiki(String(e));
    }
  }, []);

  useEffect(() => {
    const startCpp = now();
    setFibonacciResultCpp(NativeTampereJsModule.sequence(fibonacciInput));
    const endCpp = now();
    setTimeCpp(endCpp - startCpp);
    const startJs = now();
    setFibonacciResultJs(fibonacci(fibonacciInput));
    const endJs = now();
    setTimeJs(endJs - startJs);
  }, [fibonacciInput]);

  const handleInputChange = (value: string) => {
    const num = Number(value);
    if (!isNaN(num)) {
      setFibonacciInput(num);
    }
  };

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Header />
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          <Section title="Fibonacci input">
            <TextInput
              value={String(fibonacciInput)}
              onChangeText={handleInputChange}
            />
          </Section>
          <Section title="Fibonacci result C++">
            {fibonacciResultCpp} [{timeCpp}]
          </Section>
          <Section title="Fibonacci result JS">
            {fibonacciResultJs} [{timeJs}]
          </Section>
          <Section title="Time diff">{timeJs - timeCpp}</Section>
          <Section title="Wiki">{wikiContent}</Section>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
