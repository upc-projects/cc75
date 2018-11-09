//
//  SourcesViewController.swift
//  iCatchUp
//
//  Created by Developer User on 10/24/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import UIKit
import os

private let reuseIdentifier = "Cell"

class SourcesViewController: UICollectionViewController {
    var sources: [Source] = [Source]()
    var currentRow = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        NewsApi.getSources(
            apiKey: "fecf4feeffa64e4da682e7d268612ce5",
            responseHandler: handleResponse,
            errorHandler: handleError)
    }

    func handleResponse(response: SourcesResponse) {
        if let sources = response.sources {
            self.sources = sources
            self.collectionView.reloadData()
        }
    }
    
    func handleError(error: Error) {
        let message = "Error on Sources Request: \(error.localizedDescription)"
        os_log("%@", message)
    }

    override func viewWillAppear(_ animated: Bool) {
        if sources.count > 0 {
            self.collectionView.reloadItems(at: [IndexPath(row: currentRow, section: 0)])
        }
    }
    
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return sources.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! SourceCell
        // Configure the cell
        cell.update(from: sources[indexPath.row])
        return cell
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showSource" {
            let destination = segue.destination as! SourceViewController
            destination.source = sources[currentRow]
        }
    }
    
    override func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        currentRow = indexPath.row
        performSegue(withIdentifier: "showSource", sender: self)
    }

}
