/*
SPDX-License-Identifier: Apache-2.0
*/

package com.example.springbootfabricdemo.example;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;

public class ClientApp {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "false");
	}

	public static void main(String[] args) throws Exception {
        String userId = args[0];
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("src/main/java/com/example/springbootfabricdemo/example/" + "config/connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, userId).networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("fabcar");

			byte[] result;

			result = contract.evaluateTransaction("queryAllCars");
			System.out.println(new String(result));

			contract.submitTransaction("createCar", "CAR10", "VW", "Polo", "Grey", "Mary");

			result = contract.evaluateTransaction("queryCar", "CAR10");
			System.out.println(new String(result));

			contract.submitTransaction("changeCarOwner", "CAR10", "Archie");

			result = contract.evaluateTransaction("queryCar", "CAR10");
			System.out.println(new String(result));
		}
	}

}