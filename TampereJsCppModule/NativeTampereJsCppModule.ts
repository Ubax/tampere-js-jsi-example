import type {TurboModule} from 'react-native/Libraries/TurboModule/RCTExport';
// import type {TurboModule} from 'react-native'; in future versions
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  readonly sequence: (index: number) => number | undefined;
  readonly wiki: () => string;
}

export default TurboModuleRegistry.getEnforcing<Spec>('TampereJsCppModule');
