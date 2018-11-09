//
//  SourceCell.swift
//  iCatchUp
//
//  Created by Developer User on 10/31/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import UIKit

class SourceCell: UICollectionViewCell {
    @IBOutlet weak var logoImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var favoriteImageView: UIImageView!
    var isFavorite = false
    
    func update(from source: Source) {
        logoImageView.setImageFrom(
            urlString: source.urlToLogo, withDefaultNamed: "no-image", withErrorNamed: "no-image")
        nameLabel.text = source.name
        isFavorite = source.isFavorite
        favoriteImageView.setImage(fromAsset: isFavorite ?
            "favorite-icon-black" : "favorite-icon-border-black")
    }
}
