export interface EffectRequest {
  name: string;
  logicKey: string;
  description: string;
  type: 'mental' | 'physical' | 'emotional' | 'social' | 'creative' | 'all';
  targetEntities: string[];
}

export interface EffectResponse {
  id: number;
  name: string;
  logicKey: string;
  description: string;
  type: string;
  targetEntities: string[];
}
