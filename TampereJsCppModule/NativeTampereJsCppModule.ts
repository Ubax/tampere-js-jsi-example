import type {TurboModule} from 'react-native/Libraries/TurboModule/RCTExport';
// import type {TurboModule} from 'react-native'; in future versions
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  readonly sequence: (index: number) => number;
  readonly wiki: () => Promise<string>;
}

export default TurboModuleRegistry.getEnforcing<Spec>(
  'NativeTampereJsCppModule',
);
