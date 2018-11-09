//
//  SourceViewController.swift
//  iCatchUp
//
//  Created by Developer User on 10/31/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import UIKit

class SourceViewController: UIViewController {
    @IBOutlet weak var logoImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var favoriteButton: UIButton!
    
    var source: Source?
    var isFavorite = false

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if let source = source {
            logoImageView.setImageFrom(urlString: source.urlToLogo, withDefaultNamed: "no-image", withErrorNamed: "no-image")
            nameLabel.text = source.name
            descriptionLabel.text = source.description
            isFavorite = source.isFavorite
            updateFavoriteImage()
        }
    }
    
    func toggleFavorite() {
        if source != nil {
            isFavorite = !isFavorite
            self.source!.isFavorite = isFavorite
            updateFavoriteImage()
        }
    }
    
    func updateFavoriteImage() {
        favoriteButton.imageView!.setImage(fromAsset: isFavorite ?
            "favorite-icon-black" : "favorite-icon-border-black")
    }
    
    @IBAction func favoriteAction(_ sender: UIButton) {
        toggleFavorite()
    }
    
    
}
