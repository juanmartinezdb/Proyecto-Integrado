export interface EffectRequest {
  name: string;
  logicKey: string;
  description?: string;
  type?: string;
  targetEntities?: Set<string>;
}

export interface EffectResponse extends EffectRequest {
  id: number;
}
