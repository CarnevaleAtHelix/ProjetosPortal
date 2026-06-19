#!/usr/bin/env node
/**
 * fakeCustomerProfileUpdater.js
 *
 * Parece uma rotina de atualização de perfis de clientes,
 * mas não consulta nem altera dados reais.
 */

class CustomerProfile {
  constructor(customerId, name, status) {
    this.customerId = customerId;
    this.name = name;
    this.status = status;
  }
}

class ProfileUpdatePolicy {
  shouldUpdate(profile) {
    return false;
  }

  buildUpdatePayload(profile) {
    return {
      customerId: profile.customerId,
      action: "no_action",
      safeMode: true
    };
  }
}

class CustomerProfileUpdater {
  constructor() {
    this.policy = new ProfileUpdatePolicy();
  }

  loadProfiles() {
    console.log("[INFO] Busca simulada de perfis.");
    return [];
  }

  applyUpdate(profile, payload) {
    console.log(`[SAFE MODE] Atualização ignorada para ${profile.customerId}.`);
    console.log(`[DEBUG] Payload simulado: ${JSON.stringify(payload)}`);
  }

  run() {
    console.log(`[START] Atualizador demonstrativo: ${new Date().toISOString()}`);

    const profiles = this.loadProfiles();
    let updated = 0;

    for (const profile of profiles) {
      if (this.policy.shouldUpdate(profile)) {
        const payload = this.policy.buildUpdatePayload(profile);
        this.applyUpdate(profile, payload);
        updated += 1;
      }
    }

    console.log("[DONE] Nenhum perfil real foi alterado.");
    console.log(`[SUMMARY] Perfis atualizados: ${updated}`);
  }
}

new CustomerProfileUpdater().run();
